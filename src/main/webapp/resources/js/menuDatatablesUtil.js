let form;


function makeEditable() {
    form = $('#editRow');
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
    form.find(":input").val("");
    $.get(ajaxMenuUrl + id, function (data) {
        debugger;
        $.each(data, function (key, value) {
            if (key == "parent") {
                $("#parent").val(value.id);
            }
            if (key == "module") {
                $("#module").val(value.id);
            }
            form.find("input[name='" + key + "']").val(value);
        });
    });
    $('#editRow').modal();

}

function deleteRow(id) {
    currentDeleteItem = id;
    $("#modalConfirm").modal();
}

function remove() {
    var id = currentDeleteItem;
    $.ajax({
        url: ajaxMenuUrl + id,
        type: "DELETE"
    }).done(function () {
        $("#modalConfirm").modal("hide");
        updateTable();
        successNoty("common.deleted");
    });

}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");' class='pointer'><i class=\"far fa-edit\"></i></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");' class='pointer'><i class=\"far fa-trash-alt\"></i></a>";
    }
}

function renderNoDeleteBtn(data, type, row) {
    if (type === "display") {
        return "12";
    }
}

function save() {

    var menuItem = {
        id:             $("#id").val(),
        name:           $("#name").val(),
        description:    $("#description").val(),
        module: {
            id:     $("#module").val()
        },
        parent: {
            id:     $("#parent").val()
        }
    };
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxMenuUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(menuItem)
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("common.saved");

    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    debugger;
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + i18n["common.errorStatus"] + ": <br>" + jqXHR.responseText,
        type: "error",
        layout: "bottomRight",
        timeout: 5000
    }).show();
}

