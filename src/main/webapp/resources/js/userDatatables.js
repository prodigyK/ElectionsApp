const ajaxUserUrl = "rest/user/";
let datatableApi;

function updateTable() {
    $.get(ajaxUserUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxUserUrl + id,
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
            "url": ajaxUserUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "login"
            },
            {
                "data": "group.name"
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
                "data": "lastEnter",
                "render":function (data, type, row) {
                    if (type === "display") {
                        return row.lastEnter == null ? "" : row.lastEnter;
                    }
                    return data;
                }
            },
            {
                "data": "ipAddress"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderHistoryBtn
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