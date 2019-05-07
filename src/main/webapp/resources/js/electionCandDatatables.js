const ajaxElectionCandUrl = "rest/election/candidate";
const ajaxElectionTechCandUrl = "rest/election/candidate/tech/";
let datatableApi;
let datatableTechApi;

function updateTable() {
    $.get(ajaxElectionCandUrl, updateTableByData);
}

function updateTechTable(id) {
    $.get(ajaxElectionTechCandUrl + id, updateTableByData);
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxElectionCandUrl + "/enabled/" + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-candEnabled", enabled);
        chkbox.closest("tr").attr("style", "background-color:white");
        successNoty(enabled ? "common.enabled" : "common.disabled");
        updateTable();
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function leftOut(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxElectionCandUrl + "/leftOut/" + id,
        type: "POST",
        data: "leftOut=" + enabled
    }).done(function () {
        // chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function ourCand(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxElectionCandUrl + "/ourCand/" + id,
        type: "POST",
        data: "ourCand=" + enabled
    }).done(function () {
        // chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function ourTechCand(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $(chkbox).prop("checked", !enabled);
}

function init(candId){
    addEditTitle(candId);

    var chkbox = $("#top");

    if(candId === undefined){
        $("#selectTechnicals").addClass("hidden");
    } else {
        if(chkbox.is(":checked")){
            $("#selectTechnicals").addClass("show");
            openDatatableWithTechnicals(candId);
        } else {
            $("#selectTechnicals").addClass("hidden");
        }
    }
}

function checkModule(chkbox, id) {
    var enabled = chkbox.is(":checked");
    datatableTechApi.rows().eq(0).each(function (index) {
        var row = datatableTechApi.row(index);
        var data = row.data();
        if (data.id === id) {
            data.ourTechCand = enabled;
        }
    });
}

function addEditTitle(candId) {
    $("#candidate_title").html((candId != null) ? i18n["editTitle"] : i18n["addTitle"]);
}

$(function () {

    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxElectionCandUrl,
            "dataSrc": ""
        },
        "paging": true,
        "info": true,
        "pageLength": 50,
        "columns": [
            {
                "orderable": false,
                "data": "election.name"
            },
            {
                "data": "ordering"
            },
            {
                "data": "name"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "data": "leftOut",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='leftOut($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "data": "ourCand",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='ourCand($(this)," + row.id + ");'/>";
                    }
                    return data;
                }

            },
            {
                "orderable": true,
                "data": "ourTechCand",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='ourTechCand($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "orderable": false,
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            }
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).attr("data-candEnabled", false);
                return;
            }
            if(data.top){
                debugger;
                $(row).attr("style", "background-color:"+data.color+";font-weight: bold");
            }
            if(data.ourTechCand){
                debugger;
                $(row).attr("style", "background-color:"+data.techOfParent.color);
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

function openDatatableWithTechnicals(id) {

    if(datatableTechApi != undefined){
        return;
    }

    datatableTechApi = $("#datatable_technicals").DataTable({
        "ajax": {
            "url": ajaxElectionTechCandUrl + id,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "select": true,
        "searching": false,
        "ordering": true,
        "columns": [
            {
                "defaultContent": "",
                "data": "ordering"
            },
            {
                "defaultContent": "",
                "data": "name"
            },
            {
                "data": "ourTechCand",
                "orderable": false,
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
                1,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
}


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
    $.get(ajaxElectionCandUrl + id, function (data) {
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
    var savedObject = $.ajax({
        type: "POST",
        url: ajaxElectionCandUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(saveCandidate())
    }).done(function (data) {
        successNoty("common.saved");
        // $("#editRow").modal("hide");
        var id = JSON.parse(data).id;
        $("#selectTechnicals").removeClass("hidden");
        debugger;
        openDatatableWithTechnicals(id);
        $("#id").val(id);
        updateTechTable(id);
    });
}

function getDataFromTable() {
    var candidatesDataArray = [];
    datatableTechApi.rows().eq(0).each(function (index) {
        var row = datatableTechApi.row(index);
        var data = row.data();
        if (data.ourTechCand) {
            candidatesDataArray.push(data);
        }
    });

    return candidatesDataArray;
}

function saveTechnicals() {
    var checkedTechnicals = getDataFromTable();
    var candidate = {
        id:                 $("#id").val(),
        techCandidates:     checkedTechnicals
    };

    $.ajax({
        type: "POST",
        url: ajaxElectionTechCandUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(candidate)
    }).done(function (data) {
        successNoty("common.saved");
        // updateTechTable(id);
    });

}

function saveCandidate() {
    debugger;
    var candidate = {
        id:             $("#id").val(),
        name:           $("#name").val(),
        top:            $("#top").is(":checked"),
        color:          $("#color").val()
    };
    if(candidate.id === ''){
        var newCandidate = {
            id:             $("#id").val(),
            name:           $("#name").val(),
            top:            $("#top").is(":checked"),
            color:          $("#color").val(),
            election: {
                id: $("#election").val()
            }
        };
        candidate = newCandidate;
    }
    return candidate;
}


function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a href='/modules/module-election-candidates/" + row.id + "'><i class=\"far fa-edit\"></i></a>";
    }
}

var files = null;
$("#formData").on('submit', uploadFile);

function uploadFile(event) {

    event.stopPropagation();
    event.preventDefault();
    //var files = files;
    var form = $("#formData")[0];
    var data = new FormData(form);

    debugger;

    $.ajax({
        type: "POST",
        url: ajaxElectionCandUrl + "/upload",
        cache : false,
        enctype: 'multipart/form-data',
        processData : false,
        contentType : false,
        data: data
    }).done(function (data) {
        successNoty("common.saved");
        // updateTechTable(id);
    });


}