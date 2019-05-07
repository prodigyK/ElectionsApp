const ajaxMenuUrl = "/rest/menu/";
let datatableApi;
let currentDeleteItem;

function updateTable() {
    $.get(ajaxMenuUrl, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ajaxMenuUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}


$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxMenuUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "select": true,
        "ordering": false,
        "columns": [
            {
                "defaultContent": "",
                "data": "name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        if(row.parent == null){
                            return "<b>" + data + "</b>";
                        } else {
                            return "&nbsp;&nbsp;&nbsp;" + data;
                        }
                    }
                }
            },
            {
                "defaultContent": "",
                "data": "parent.name"
            },
            {
                "defaultContent": "",
                "data": "module.name"
            },
            {
                "defaultContent": "",
                "render": renderEditBtn

            },
            {
                "defaultContent": "",
                "render": function (data, type, row) {
                    if(type === "display"){
                        if(row.parent == null){
                            debugger;
                            return "";
                        } else {
                            debugger;
                            return renderDeleteBtn(data, type, row);
                        }
                    }
                }
            }
        ],
        "initComplete": makeEditable
    });
});
