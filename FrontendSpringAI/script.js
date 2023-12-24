const apiUrl = "http://localhost:8080";

function testAiPrompt() {
    fetch(apiUrl + "/prompt/hello")
        .then(handleResponse)
        .catch(handleError);
}

function getJoke() {
    const topic = prompt("Enter a topic:");
    fetch(apiUrl + `/prompt/learn/${topic}`)
        .then(handleResponse)
        .catch(handleError);
}

function getTopLanguage() {
    const year = prompt("Enter a year:");
    fetch(apiUrl + `/prompt/getLanguage/${year}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById("aiResponse").innerText = JSON.stringify(data);
            document.getElementById("aiResponse").style.color = "green";
        })
        .catch(handleError);
}

function addFeedback() {
    const userFeedback = prompt("Enter your feedback:");
    fetch(apiUrl + "/feedback/addfeedback", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ userFeedback }),
    })
        .then(handleResponse)
        .catch(handleError);
}

function getAllFeedback() {
    fetch(apiUrl + "/feedback/getAllFeedback")
        .then(response => response.json())
        .then(data => {
            document.getElementById("feedbackResponse").innerText = JSON.stringify(data);
            document.getElementById("feedbackResponse").style.color = "green";
        })
        .catch(handleError);
}

function deleteFeedback() {
    const feedbackId = prompt("Enter the feedback ID to delete:");
    fetch(apiUrl + `/feedback/deleteFeedback/${feedbackId}`, {
        method: "DELETE",
    })
        .then(handleResponse)
        .catch(handleError);
}

function updateFeedback() {
    const feedbackId = prompt("Enter the feedback ID to update:");
    const updatedFeedback = prompt("Enter the updated feedback:");
    fetch(apiUrl + `/feedback/updateFeedback/${feedbackId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ feedback: updatedFeedback }),
    })
        .then(handleResponse)
        .catch(handleError);
}

function handleResponse(response) {
    if (response.ok) {
        return response.text().then(data => {
            document.getElementById("aiResponse").innerText = data;
            document.getElementById("aiResponse").style.color = "green";
        });
    } else {
        throw new Error("Network response was not ok.");
    }
}

function handleError(error) {
    console.error("Fetch error:", error);
    document.getElementById("aiResponse").innerText = "Error!";
    document.getElementById("aiResponse").style.color = "red";
}
