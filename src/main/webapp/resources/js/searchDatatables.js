const ajaxSearchUrl = "rest/search/";
let datatableApi;


function updateTable() {
    $.get(ajaxSearchUrl, updateTableByData);
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function getPeople() {

    loadingAdd($("#search_button"));

    var person = {
        iin: $("#iin").val(),
        passport: $("#passport").val(),
        lastname: $("#lastname").val(),
        firstname: $("#firstname").val(),
        middlename: $("#middlename").val(),
        region: $("#region").val(),
        city: $("#city").val(),
        district: $("#district").val(),
        street: $("#street").val(),
        house: $("#houseNumber").val()
    };
    debugger;

    var result;
    $.ajax({
        type: "POST",
        url: ajaxSearchUrl,
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(person),
        success: function (response) {
            result = JSON.parse(response);
            debugger;
            openDatatable(result);
            loadingRemove($("#search_button"));
        },
        error: function () {
            loadingRemove($("#search_button"));
        }
    });

}

function loadingAdd(element) {
    element.addClass("loading disabled");
}

function loadingRemove(element) {
    element.removeClass("loading disabled");
}

function clearForm() {
    // $("#region").val('');
    // $("#city").val('');
    $("#district").val('');
    $("#street").val('');
    $("#houseNumber").val('');
    $("#lastname").val('');
    $("#firstname").val('');
    $("#middlename").val('');
    $("#iin").val('');
    $("#passport").val('');
    $("#datatable_div").addClass("hidden");
}

function init() {
    // $("#datatable_div").addClass("hidden");
}


function openDatatable(result) {

    if (result == null) {
        return;
    }
    $("#datatable_div").removeClass("hidden");


    $(function () {
        datatableApi = $("#datatable").DataTable({
            "data": result,
            "destroy": true,
            "paging": false,
            "pageLength": 25,
            "info": false,
            "searching": false,
            "ordering": false,
            "columns": [
                {
                    "data": "id",
                    "visible": false
                },
                {
                    "data": "lastname"
                },
                {
                    "data": "firstname"
                },
                {
                    "data": "middlename"
                },
                {
                    "data": "district"
                },
                {
                    "data": "street"
                },
                {
                    "data": "house"
                },
                {
                    "data": "flat"
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
            "rowId": 'id'

        });
    });

}

$(document).ready(function () {

    var result = "";

    $('#lastname').focusout(function () {
        result = "";
        closeAllDropdowns();
    });

    $('#lastname').keyup(function () {

        var query = $('#lastname').val();
        if (query.length < 3) {
            return;
        }

        $.get(ajaxSearchUrl + "lastname/" + query, function (data) {

            result = "";
            $.each(data, function (key, value) {

                result += value + "<br>";
            });

            $("#lastname_results").removeClass("hidden");

            $("#lastname_results").html(result);
            $("#lastname_results").css("border", "1px solid #A5ACB2");

        });
    });

    $("input").keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            getPeople();
            closeAllDropdowns();
        }
    });

});


$(document).ready(function () {

    var result = "";

    $('#firstname').focusout(function () {
        result = "";
        closeAllDropdowns();
    });

    $('#firstname').keyup(function () {

        var query = $('#firstname').val();
        if (query.length < 3) {
            return;
        }

        $.get(ajaxSearchUrl + "firstname/" + query, function (data) {

            result = "";
            $.each(data, function (key, value) {

                result += value + "<br>";
            });

            $("#firstname_results").removeClass("hidden");

            $("#firstname_results").html(result);
            $("#firstname_results").css("border", "1px solid #A5ACB2");

        });
    });
});

$(document).ready(function () {

    var result = "";

    $('#middlename').focusout(function () {
        result = "";
        closeAllDropdowns();
    });

    $('#middlename').keyup(function () {

        var query = $('#middlename').val();
        if (query.length < 3) {
            return;
        }

        $.get(ajaxSearchUrl + "middlename/" + query, function (data) {

            result = "";
            $.each(data, function (key, value) {

                result += value + "<br>";
            });

            $("#middlename_results").removeClass("hidden");

            $("#middlename_results").html(result);
            $("#middlename_results").css("border", "1px solid #A5ACB2");

        });
    });
});

$(document).ready(function () {

    var result = "";

    $('#street').focusout(function () {
        result = "";
        closeAllDropdowns();
    });

    $('#street').keyup(function () {

        var query = $('#street').val();
        if (query.length < 1) {
            return;
        }

        $.get(ajaxSearchUrl + "street/" + query, function (data) {

            result = "";
            $.each(data, function (key, value) {

                result += value + "<br>";
            });

            $("#street_results").removeClass("hidden");

            $("#street_results").html(result);
            $("#street_results").css("border", "1px solid #A5ACB2");

        });
    });

});

function closeAllDropdowns() {

    $("#lastname_results").addClass("hidden");
    $("#firstname_results").addClass("hidden");
    $("#middlename_results").addClass("hidden");
    $("#street_results").addClass("hidden");
}


$(document).ready(function () {

    $('#datatable').on('dblclick', 'tr', function (data) {

        debugger;
        var id = datatableApi.row(this).id();

        window.open("modules/module-add-person/edit/" + id);
    });

});

function openForm() {
    debugger;
    window.open("modules/module-add-person");
}