let xField = document.getElementById("pointCheckForm:xField");
xField.required = true;
function checkX(){
    if (xField.value <= 3 && xField.value >= -5) {
        xField.setCustomValidity("");
    } else {
        xField.setCustomValidity("Требуется число от -5 до 3!")
    }
}

let yField = document.getElementById("pointCheckForm:yField");
yField.required = true;
function checkY(){
    if (yField.value <= 5 && yField.value >= -3) {
        yField.setCustomValidity("");
    } else {
        yField.setCustomValidity("Требуется число от -3 до 5!")
    }
}

function changeCheckBoxBehavior(element)
{
    let checkboxes = document.querySelectorAll("input[type=checkbox]");
    for (let i = 0; i < checkboxes.length; i++)
    {
        checkboxes[i].checked = false;
    }
    element.checked = true;
}