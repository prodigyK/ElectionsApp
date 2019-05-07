const ajaxElectionUrl = "rest/election/";
let datatableApi;

function updateTable() {
    $.get(ajaxElectionUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxElectionUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxElectionUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "fullName"
            },
            {
                "data": "electionDate"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            }
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).attr("data-userEnabled", false);
            }
        },
        "order": [
            [
                3,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});

let form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        debugger;
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxElectionUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxElectionUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(saveElection())
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("common.saved");
    });
}

function saveElection() {
    var election = {
        id:             $("#id").val(),
        name:           $("#name").val(),
        fullName:       $("#fullName").val(),
        electionDate:   $("#electionDate").val()
    };
    return election;
}



function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");' class='pointer'><i class=\"far fa-edit\"></i></a>";
    }
}
