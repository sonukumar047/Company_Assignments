document.getElementById('getProfile').addEventListener('click', function() {
    const username = prompt('Enter username:');
    if (username) {
        fetch(`http://localhost:8888/users/${username}`)
            .then(response => response.json())
            .then(data => {
                displayProfile(data);
            })
            .catch(error => console.error('Error:', error));
    }
});

document.getElementById('saveProfile').addEventListener('click', function() {
    const username = prompt('Enter username:');
    if (username) {
        fetch(`http://localhost:8888/saveUsers/${username}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            displayResult(data);
        })
        .catch(error => console.error('Error:', error));
    }
});

document.getElementById('getAllProfiles').addEventListener('click', function() {
    fetch('http://localhost:8888/allUsers')
        .then(response => response.json())
        .then(data => {
            displayProfiles(data);
        })
        .catch(error => console.error('Error:', error));
});

document.getElementById('getAllRepos').addEventListener('click', function() {
    const username = prompt('Enter username:');
    if (username) {
        fetch(`http://localhost:8888/userRepos/${username}`)
            .then(response => response.json())
            .then(data => {
                displayRepositories(data);
            })
            .catch(error => console.error('Error:', error));
    }
});

document.getElementById('saveAllRepos').addEventListener('click', function() {
    const username = prompt('Enter username:');
    if (username) {
        fetch(`http://localhost:8888/userRepos/${username}`, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            displayResult(data);
        })
        .catch(error => console.error('Error:', error));
    }
});

function displayProfile(profile) {
    const mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = `
        <div class="profile-info">
            <img src="${profile.avatar_url}" alt="Profile Avatar">
            <div>
                <h2>${profile.name}</h2>
                <p>ID: ${profile.id}</p>
                <p>Type: ${profile.type}</p>
                <p>Site Admin: ${profile.site_admin}</p>
                <p>Bio: ${profile.bio || 'No bio available.'}</p>
                <p>Location: ${profile.location || 'Location not specified.'}</p>
                <p>GitHub URL: <a href="${profile.html_url}" target="_blank">${profile.html_url}</a></p>
            </div>
        </div>`;
    // Add other details as needed
}





function displayProfiles(profiles) {
    const mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = '<h2>All Profiles</h2>';

    if (profiles && profiles.length > 0) {
        profiles.forEach(profile => {

            const repoCard = document.createElement('div');
            repoCard.classList.add('repo-card');

            repoCard.innerHTML += `
                <div class="profile-info">
                    <img src="${profile.avatar_url}" alt="Profile Avatar">
                    <div>
                        <h2>${profile.name}</h2>
                        <p>ID: ${profile.id}</p>
                        <p>Type: ${profile.type}</p>
                        <p>Site Admin: ${profile.site_admin}</p>
                        <p>Bio: ${profile.bio || 'No bio available.'}</p>
                        <p>Location: ${profile.location || 'Location not specified.'}</p>
                        <p>GitHub URL: <a href="${profile.html_url}" target="_blank">${profile.html_url}</a></p>
                    </div>
                </div>
            `;
            mainContent.appendChild(repoCard);
        });
    } else {
        mainContent.innerHTML += '<p>No profiles found.</p>';
    }
}


// function displayRepositories(repositories) {
//     const mainContent = document.getElementById('mainContent');
//     mainContent.innerHTML = '<h2>All Repositories</h2>';
//     if (repositories.length === 0) {
//         mainContent.innerHTML += '<p>No repositories found.</p>';
//     } else {
//         repositories.forEach(repo => {
//             const repoCard = document.createElement('div');
//             repoCard.classList.add('repo-card');

//             repoCard.innerHTML = `
//                 <h3><a href="${repo.html_url}" target="_blank">${repo.name}</a></h3>
//                 <p>${repo.description || 'No description available.'}</p>
//                 <p><strong>Created at:</strong> ${repo.created_at}</p>
//                 <p><strong>Open Issues:</strong> ${repo.open_issues}</p>
//                 <p><strong>Watchers:</strong> ${repo.watchers}</p>
//                 <p><strong>Clone URL:</strong> <a href="${repo.clone_url}" target="_blank">${repo.clone_url}</a></p>
//                 <img src="${repo.owner.avatar_url}" alt="Owner Avatar">
//                 <p><strong>Owner:</strong> <a href="${repo.owner.html_url}" target="_blank">${repo.owner.html_url}</a></p>
//             `;

//             mainContent.appendChild(repoCard);
//         });
//     }
// }

function displayRepositories(repositories) {
    const mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = '<h2>All Repositories</h2>';
    
    if (repositories && repositories.length > 0) {
        repositories.forEach(repo => {
            const repoCard = document.createElement('div');
            repoCard.classList.add('repo-card');

            repoCard.innerHTML = `
                <h3><a href="${repo.html_url}" target="_blank">${repo.name}</a></h3>
                <p>${repo.description || 'No description available.'}</p>
                <p><strong>Created at:</strong> ${repo.created_at}</p>
                <p><strong>Open Issues:</strong> ${repo.open_issues}</p>
                <p><strong>Watchers:</strong> ${repo.watchers}</p>
                <p><strong>Clone URL:</strong> <a href="${repo.clone_url}" target="_blank">${repo.clone_url}</a></p>
                <div class="owner-info">
                    <img src="${repo.owner.avatar_url}" alt="Owner Avatar">
                    <p><strong>Owner:</strong> <a href="${repo.owner.html_url}" target="_blank">${repo.owner.html_url}</a></p>
                </div>
            `;

            mainContent.appendChild(repoCard);
        });
    } else {
        mainContent.innerHTML += '<p>No repositories found.</p>';
    }
}


function displayResult(result) {
    const mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = `<p>${result.message || 'Operation completed successfully.'}</p>`;
}