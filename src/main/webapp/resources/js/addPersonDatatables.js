const ajaxAddPersonUrl = "rest/person/";
const ajaxSearchUrl = "rest/search/";
let datatableApi;

$(document).ready(function () {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        debugger;
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
})

function init_onload(userId) {

    if (userId === undefined) {
        debugger;
        return;
    }

    var byAjax;
    $.ajax({
        type: "GET",
        url: ajaxAddPersonUrl + userId,
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            fillForm(response);

        },
        error: function () {
            debugger;
            var v = 12;
        }
    });


}

function fillForm(person) {
    debugger;

    $("#id").val(person.id);
    $("#iin").val(person.iin);
    $("#passport").val(person.passport);
    $("#lastname").val(person.lastname);
    $("#firstname").val(person.firstname);
    $("#middlename").val(person.middlename);
    $("#birthday").val(person.birthday);
    $("#phone").val(person.phone);
    $("#email").val(person.email);
    $("#dateOfIssue").val(person.dateOfIssue);
    if (person.storonnik === true) {
        $("#type_storonnik").prop('checked', true);
    }

    if (person.addressRegistration != null) {

        $("#id_reg").val(person.addressRegistration.id);
        $("#mailindex_reg").val(person.addressRegistration.mailIndex);
        $("#region_reg").val(person.addressRegistration.region.id);
        $("#city_reg").val(person.addressRegistration.city.id);
        $("#district_reg").val(person.addressRegistration.district.id);
        $("#id_house_reg").val(person.addressRegistration.house.id);
        $("#house_reg").val(person.addressRegistration.house.houseNumber);
        $("#street_reg").val(person.addressRegistration.house.street.id);
        $("#corp_reg").val(person.addressRegistration.house.corps);
        $("#letter_reg").val(person.addressRegistration.house.letter);
        $("#flat_reg").val(person.addressRegistration.flat);
    }

    if (person.livingForRegistration === true) {
        $("#living_for_registration").prop('checked', true);

        livingPlaceBlockDisable();
    } else {
        if (person.addressResidential != null) {

            $("#id_reg_res").val(person.addressResidential.id);
            $("#mailindex").val(person.addressResidential.mailIndex);
            $("#region").val(person.addressResidential.region.id);
            $("#city").val(person.addressResidential.city.id);
            $("#district").val(person.addressResidential.district.id);
            $("#id_house").val(person.addressResidential.house.id);
            $("#house").val(person.addressResidential.house.houseNumber);
            $("#street").val(person.addressResidential.house.street.id);
            $("#corp").val(person.addressResidential.house.corps);
            $("#letter").val(person.addressResidential.house.letter);
            $("#flat").val(person.addressResidential.flat);
        }
    }
}

function livingPlaceBlockDisable() {
    $("#mailindex").val($("#mailindex_reg").val());
    $("#region").val($("#region_reg").val());
    $("#city").val($("#city_reg").val());
    $("#district").val($("#district_reg").val());
    $("#house").val($("#house_reg").val());
    $("#street").val($("#street_reg").val());
    $("#corp").val($("#corp_reg").val());
    $("#letter").val($("#letter_reg").val());
    $("#flat").val($("#flat_reg").val());

    $("#living_place_block").addClass('disabled');
    $("#living_place_block_2").addClass('disabled');

}

function livingPlaceBlockEnable() {
    $("#living_place_block").removeClass('disabled');
    $("#living_place_block_2").removeClass('disabled');
}

$(document).ready(function () {
    $("#living_for_registration").click(function () {
        var enabled = $("#living_for_registration").is(":checked");
        if (enabled) {
            livingPlaceBlockDisable();
        } else {
            livingPlaceBlockEnable()
        }
    });
})



function savePerson() {
    var storonnik = $("#type_storonnik").is(":checked");
    var livingForRegistration = $("#living_for_registration").is(":checked");

    debugger;

    if($("#region_reg").val() === "" ||
        $("#city_reg").val() === "" ||
        $("#district_reg").val() === "" ||
        $("#street_reg").val() === ""){
        alert("Не заполнены все поля адреса");
            return;
    }
    if(!livingForRegistration){
        if($("#region").val() === "" ||
            $("#city").val() === "" ||
            $("#district").val() === "" ||
            $("#street").val() === ""){
            alert("Не заполнены все поля адреса");
            return;
        }
    }

    var person = {
        id: $("#id").val(),
        lastname: $("#lastname").val(),
        firstname: $("#firstname").val(),
        middlename: $("#middlename").val(),
        birthday: $("#birthday").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        iin: $("#iin").val(),
        passport: $("#passport").val(),
        dateOfIssue: $("#dateOfIssue").val(),
        addressRegistration: {
            id: $("#id_reg").val(),
            region: {
                id: $("#region_reg").val()
            },
            city: {
                id: $("#city_reg").val()
            },
            district: {
                id: $("#district_reg").val()
            },
            house: {
                id: $("#id_house_reg").val(),
                street: {
                    id: $("#street_reg").val()
                },
                houseNumber: $("#house_reg").val(),
                corps: $("#corp_reg").val(),
                letter: $("#letter_reg").val()
            },
            mailIndex: $("#mailindex_reg").val(),
            flat: $("#flat_reg").val()
        },
        addressResidential: {
            id: $("#id_reg_res").val(),
            region: {
                id: $("#region").val()
            },
            city: {
                id: $("#city").val()
            },
            district: {
                id: $("#district").val()
            },
            house: {
                id: $("#id_house").val(),
                street: {
                    id: $("#street").val()
                },
                houseNumber: $("#house").val(),
                corps: $("#corp").val(),
                letter: $("#letter").val()
            },
            mailIndex: $("#mailindex").val(),
            flat: $("#flat").val()
        },
        storonnik: storonnik,
        livingForRegistration: livingForRegistration
    };
    debugger;

    var result;
    $.ajax({
        type: "POST",
        url: ajaxAddPersonUrl + "add",
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(person),
        success: function (response) {
            debugger;
            successNoty("common.saved");
            clearForm();
            sleep(3000);
            window.open("modules/module-add-person");
        },
        error: function () {
            debugger;
            var v = 12;
        }
    });


    var test = 10;

}


function save() {


}

function clearForm() {

    $("#id").val('');
    $("#iin").val('');
    $("#passport").val('');
    $("#lastname").val('');
    $("#firstname").val('');
    $("#middlename").val('');
    $("#birthday").val('');
    $("#phone").val('');
    $("#email").val('');
    $("#dateOfIssue").val('');
    $("#type_storonnik").prop('checked', false);

    $("#id_reg").val('');
    $("#mailindex_reg").val('');
    $("#region_reg").val('');
    $("#city_reg").val('');
    $("#district_reg").val('');
    $("#id_house_reg").val('');
    $("#house_reg").val('');
    $("#street_reg").val('');
    $("#corp_reg").val('');
    $("#letter_reg").val('');
    $("#flat_reg").val('');

    $("#living_for_registration").prop('checked', false);

    $("#id_reg_res").val('');
    $("#mailindex").val('');
    $("#region").val('');
    $("#city").val('');
    $("#district").val('');
    $("#id_house").val('');
    $("#house").val('');
    $("#street").val('');
    $("#corp").val('');
    $("#letter").val('');
    $("#flat").val('');

    livingPlaceBlockEnable();
}


$(document).ready(function () {

    var result = "";

    $('#lastname').focusout(function () {
        result = "";
        $("#lastname_results").addClass("hidden");
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
            // getPeople();
            // alert("Проверьте данные!!!")
        }
    });
});


$(document).ready(function () {

    var result = "";

    $('#firstname').focusout(function () {
        result = "";
        $("#firstname_results").addClass("hidden");
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
        $("#middlename_results").addClass("hidden");
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
