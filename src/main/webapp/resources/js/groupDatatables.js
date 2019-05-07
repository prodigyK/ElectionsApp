const ajaxGroupUrl = "rest/group/";
const ajaxGroupModulesUrl = ajaxGroupUrl + "module/";
const ajaxModuleUrl = "rest/module/";
let datatableApi;
let datatableModuleApi;

function updateTable() {
    $.get(ajaxGroupUrl, updateTableByData);
    $.get(ajaxModuleUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxGroupUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}


function checkModule(chkbox, id) {
    var enabled = chkbox.is(":checked");
    datatableModuleApi.rows().eq(0).each(function (index) {
        var row = datatableModuleApi.row(index);
        var data = row.data();
        if (data.id === id) {
            data.enabledInGroup = enabled;
        }
    });
}



// $(document).ready(function () {

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxGroupUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "select": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "description"
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
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});

function openDatatable(groupId) {

    $("#group_title").html((groupId == null) ? i18n["addTitle"] : i18n["editTitle"])

    var ajaxUrl;
    if (groupId == null) {
        ajaxUrl = ajaxModuleUrl;
    } else {
        ajaxUrl = ajaxGroupModulesUrl + groupId;
    }

    $(function () {
        datatableModuleApi = $("#datatable_modules").DataTable({
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": true,
            "info": true,
            "columns": [
                {
                    "data": "id"
                },
                {
                    "data": "menuParent"
                },
                {
                    "data": "menuChild"
                },
                {
                    "data": "name"
                },
                {
                    "data": "description"
                },
                {
                    "data": "enabledInGroup",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='checkModule($(this)," + row.id + ");' />";
                        }
                        return data;
                    }
                }
            ],
            "order": [
                [
                    5,
                    "desc"
                ]
            ],
            "initComplete": makeEditable
        });
    });

}