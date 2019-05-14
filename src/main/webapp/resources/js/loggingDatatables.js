const ajaxLoggingUrl = "rest/logging/";
const ajaxLoggingDetailsUrl = "rest/logging/details/";
let datatableApi;
let datatableChangesApi;

function updateTable() {
    $.get(ajaxLoggingUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxLoggingUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function init(dateFrom, dateTo) {
    $("#dateFrom").val(dateFrom);
    $("#dateTo").val(dateTo);
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxLoggingUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "id",
                "visible": false
            },
            {
                "data": "timeChange",
                "render": function (data, type, row) {
                    if (type === "display") {
                        debugger;
                        return formatDate(data);
                    }
                    return data;
                }
            },
            {
                "data": "user.login"
            },
            {
                "data": "user.name"
            },
            {
                "data": "subscriber.id",
                "render": function (data, type, row) {
                    if (type === "display") {
                        debugger;
                        return "<a href='modules/module-add-person/edit/" + data + "' target='_blank'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "isNew",
                "render": function (data, type, row) {
                    if (type === "display") {
                        debugger;
                        return data ? "Так" : "Нi"
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderHistoryBtn
            }
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.isNew) {
                debugger;
                $(row).attr("style", "background-color: #D5FFD7");
            }
        },
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "initComplete": makeEditable
    });
});


function createDatatable(url) {

    datatableChangesApi = $("#datatable_changes").DataTable({
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "ordering": false,
        "searching": false,
        "columns": [
            {
                "data": "fieldName",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<b>" + data + "</b>";
                    }
                    return data;
                }
            },
            {
                "data": "previousValue"
            },
            {
                "data": "newValue"
            }
        ]
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function updateTableDetailsByData(data) {
    datatableChangesApi.clear().rows.add(data).draw();
}

function formatDate(date) {
    return date.replace('T', ' ').substr(0, 16);
}

function renderHistoryBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='historyRow(" + row.id + ");' class='pointer'><i class=\"fas fa-history\"></i></a>";
    }
}

function historyRow(id) {
    $.ajax({
        url: ajaxLoggingDetailsUrl + id,
        type: "GET"
    }).done(function (response) {

        if (datatableChangesApi === undefined) {
            createDatatable(ajaxLoggingDetailsUrl + id);
        } else {
            updateTableDetailsByData(response);
        }
        $("#myModal").modal();
    });

}

function showResult() {
    var filter = {
        userId: $("#username").val(),
        dateFrom: $("#dateFrom").val(),
        dateTo: $("#dateTo").val()
    }

    debugger;

    $.ajax({
        type: "POST",
        url: ajaxLoggingUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(filter),
        success: function (response) {
            debugger;
            var result = JSON.parse(response);
            updateTableByData(result);
        },
        error: function () {

        }
    });
}

function makeEditable() {
    // form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        debugger;
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}