let canvas = document.getElementById('canvas');
let context = canvas.getContext('2d');
canvas.addEventListener('mouseup', function (e) {
    let x = e.pageX - e.target.offsetLeft;
    let y = e.pageY - e.target.offsetTop;
    document.getElementById("hiddenField").value = ((x - 133)/36+0.01).toFixed(1);
    document.getElementsByName("coordinateY")[0].value = ((y - 133)/-36+0.005).toFixed(1);
    document.getElementById("pointCheckForm").submit();
});

function drawArea(r) {
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "black";
    context.lineWidth = 1;

    context.beginPath(); // оси
    context.moveTo(132.5, 0);
    context.lineTo(132.5, 264);
    context.moveTo(128, 6);
    context.lineTo(132.5, 0);
    context.moveTo(137, 6);
    context.lineTo(132.5, 0);
    context.moveTo(0, 132.5);
    context.lineTo(264, 132.5);
    context.moveTo(258, 137);
    context.lineTo(264, 132.5);
    context.moveTo(258, 128);
    context.lineTo(264, 132.5);
    context.stroke();

    context.font = "11pt Calibri"; // подписи осей
    context.fillText("Y", 120, 11);
    context.fillText("X", 255, 148);

    context.fillStyle = "#4a98ff";
    context.fillRect(132.5-r*18*2-1.5, 133, r*18*2+1, r*18*2+1); // прямоугольник

    context.beginPath(); // треугольник
    context.moveTo(133, 132);
    context.lineTo(133+r*18*2+1.5, 132);
    context.lineTo(133, 132-r*18);
    context.lineTo(133, 132);
    context.fill();

    context.beginPath(); // круг
    context.arc(131.5, 132, r*18, -Math.PI/2, -Math.PI, true);
    context.lineTo(132, 132);
    context.lineTo(132, 132-r*18);
    context.fill();

    for(let i = 1; i <= 6; i++) {
        context.beginPath();
        context.moveTo(132.5+i*18+1, 130); // насечки по x вправо
        context.lineTo(132.5+i*18+1, 135);
        context.moveTo(132.5-i*18-1, 130); // насечки по x влево
        context.lineTo(132.5-i*18-1, 135);
        context.moveTo(130, 132.5+i*18+1); // насечки по y вниз
        context.lineTo(135, 132.5+i*18+1);
        context.moveTo(130, 132.5-i*18-1); // насечки по y вверх
        context.lineTo(135, 132.5-i*18-1);
        context.stroke();
    }

    context.fillStyle = "black";

    context.fillText("R", 120, 132.5-r*18*2+2); // R по y
    if (r !== 1) {
        context.fillText("0.5R", 120-19, 132.5-r*18+2);
        context.fillText("0.5R", 120-19, 132.5+r*18+4);
    }
    context.fillText("R", 120, 132.5+r*18*2+4);

    context.fillText("R", 132.5-r*18*2-5, 148); // R по x
    if (r !== 1) {
        context.fillText("0.5R", 132.5 - r * 18 - 10, 148);
        context.fillText("0.5R", 132.5 + r * 18 - 8, 148);
    }
    context.fillText("R", 132.5 + r * 18 * 2 - 3, 148);

    //drawPointsFromHistory(); // заполняем точки из истории
}
drawArea(3);