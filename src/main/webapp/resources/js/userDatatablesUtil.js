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
    $.get(ajaxUserUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key == "group") {
                $("#group").val(value.id);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function historyRow(id) {
    $.ajax({
        url: ajaxUserUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("common.deleted");
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUserUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(saveUser())
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("common.saved");
    });
}

function saveUser() {
    var user = {
        id:             $("#id").val(),
        name:           $("#name").val(),
        identNumber:    $("#identNumber").val(),
        passport:       $("#passport").val(),
        login:          $("#login").val(),
        password:       $("#password").val(),
        group: {
            id:     $("#group").val()
        }
    };
    return user;
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

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");' class='pointer'><i class=\"far fa-edit\"></i></a>";
    }
}

function renderHistoryBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='historyRow(" + row.id + ");' class='pointer'><i class=\"fas fa-history\"></i></a>";
    }
}