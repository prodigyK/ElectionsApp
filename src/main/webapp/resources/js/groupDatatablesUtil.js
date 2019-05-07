let form;


function makeEditable() {
    form = $('#groupForm');
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

function updateRow(id, name) {
    // $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxGroupUrl + "module/" + id, function (data) {
        debugger;
        datatableModuleApi.clear().rows.add(data).draw();
    });
    $.get(ajaxGroupUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
    });

    $("#modules_title").html("Модули группы \"" + name + "\"");
}


function cancel() {
    // window.redirect("/modules/module-groups", 200);
    $(location).attr('href',"/modules/module-groups");
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a href='/modules/module-groups/" + row.id + "'><i class=\"far fa-edit\"></i></a>";
    }
}

function getDataFromTable() {
    var modulesDataArray = [];
    datatableModuleApi.rows().eq(0).each(function (index) {
        debugger;
        var row = datatableModuleApi.row(index);
        var data = row.data();
        if (data.enabledInGroup) {
            debugger;
            rowData = {
                id: data.id,
                enabledInGroup: data.enabledInGroup
            };
            modulesDataArray.push(data);
        }
    });

    return modulesDataArray;
}


function save() {

    var checkedModules = getDataFromTable();
    var group = {
        id:             $("#id").val(),
        name:           $("#name").val(),
        description:    $("#description").val(),
        modules:        checkedModules
    };
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxGroupUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(group)
    }).done(function () {
        // updateTable();
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

