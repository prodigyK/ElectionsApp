const ajaxModuleUrl = "rest/module/";
let datatableApi;

function updateTable() {
    $.get(ajaxModuleUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxModuleUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}
function visible(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    debugger;
    $.ajax({
        url: ajaxModuleUrl + "0/" + id,
        type: "POST",
        data: "visible=" + enabled
    }).done(function () {
        successNoty(enabled ? "common.visible" : "common.invisible");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxModuleUrl,
            "dataSrc": ""
        },
        "paging": true,
        "pageLength": 25,
        "info": true,
        "columns": [
            {
                "data": "menuParent"
            },
            {
                "data": "menuChild"
            },
            {
                "data": "permission"
            },
            {
                "data": "name"
            },
            {
                "data": "description"
            },
            {
                "data": "enabled",
                "orderable": false,
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "data": "visible",
                "orderable": false,
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='visible($(this)," + row.id + ");'/>";
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
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});