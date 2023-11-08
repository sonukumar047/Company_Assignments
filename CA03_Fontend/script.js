// const express = require('express');
// const app = express();

// // Handle favicon.ico request
// app.get('/favicon.ico', (req, res) => res.status(204));

// // Other routes and middleware...

// app.listen(3000, () => {
//   console.log('Server is running on port 3000');
// });


function addCar() {
    var carNumber = document.getElementById("carNumber").value;
    fetch(`http://localhost:8080/car/addCar`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ carNumber: carNumber })
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response from the backend
        console.log(data);
    });
}

function getCarById() {
    var carId = document.getElementById("carId").value;
    fetch(`http://localhost:8080/car/getCarById/${carId}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById("carByIdResult").textContent = JSON.stringify(data);
    });
}

function getCarByNumber() {
    var carNumber = document.getElementById("carNumberInput").value;
    fetch(`http://localhost:8080/car/getByCarNumber/${carNumber}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById("carByNumberResult").textContent = JSON.stringify(data);
    });
}

function getAllCars() {
    fetch(`http://localhost:8080/car/getAllCar`)
    .then(response => response.json())
    .then(data => {
        var allCarsList = document.getElementById("allCarsList");
        allCarsList.innerHTML = '';
        data.forEach(car => {
            var listItem = document.createElement("li");
            listItem.textContent = `Car ID: ${car.carId}, Car Number: ${car.carNumber}`;
            allCarsList.appendChild(listItem);
        });
    });
}

function parkCar() {
    var carNumber = document.getElementById("parkCarNumber").value;
    fetch(`http://localhost:8080/parking/park/${carNumber}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response from the backend
        console.log(data);
    });
}

function getParkingDetailsBySlotId() {
    var slotId = document.getElementById("slotId").value;
    fetch(`http://localhost:8080/parking/getParkBySlotId/${slotId}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById("parkingDetailsResult").textContent = JSON.stringify(data);
    });
}

function getAllParkingSlots() {
    fetch(`http://localhost:8080/parking/getAllParkingSlot`)
    .then(response => response.json())
    .then(data => {
        var parkingSlotsList = document.getElementById("parkingSlotsList");
        parkingSlotsList.innerHTML = '';
        data.forEach(slot => {
            var listItem = document.createElement("li");
            listItem.textContent = `Slot ID: ${slot.slotId}, Car Number: ${slot.carNumber}`;
            parkingSlotsList.appendChild(listItem);
        });
    });
}

function unparkCar() {
    var slotId = document.getElementById("unparkSlotId").value;
    fetch(`http://localhost:8080/parking/unpark/${slotId}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(data => {
        // Handle the response from the backend
        console.log(data);
    });
}
