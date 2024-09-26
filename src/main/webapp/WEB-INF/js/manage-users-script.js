function onClickUpdate(element) {
    var form = element.form;
    var formElements= form.elements;
    for (var i = 0, len = formElements.length; i < len; ++i) {
        formElements[i].disabled = false;
    }
}