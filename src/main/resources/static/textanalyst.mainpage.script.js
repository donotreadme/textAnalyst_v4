function displayTextByTitle(title) {
    title = encodeURIComponent(title)
    const content = `http://localhost:8080/text/findByTitle?title=${title}`;
    fetch(content)
        .then(response => response.json())
        .then(data => {
            const textarea = document.getElementById('floatingTextarea');
            textarea.value = data[0].text;
            updateMetrics(data[0].metric);
        })
        .catch(error => {
            console.error('Error fetching text by title:', error);
        });
}

function updateMetrics(textMetrics) {
    const numberParagraphs = document.getElementById('paragraphs');
    const numberSentences = document.getElementById('sentences');
    const numberWords = document.getElementById('words');
    const numberCharacters = document.getElementById('characters');
    numberParagraphs.innerHTML = textMetrics.numberOfParagraphs;
    numberSentences.innerHTML = textMetrics.numberOfSentences;
    numberWords.innerHTML = textMetrics.numberOfWords;
    numberCharacters.innerHTML = textMetrics.numberOfCharacters;
}

function loadTitles() {
    fetch('http://localhost:8080/text') // TODO: change to the correct api
        .then(response => response.json())
        .then(data => {
            const dropdownItems = document.getElementById('titlesDropdown');
            // Loop through the JSON data and create dropdown items
            data.forEach(item => {
                const title = item.title;
                const menuItem = document.createElement('a');
                menuItem.className = 'dropdown-item';
                menuItem.textContent = title;
                menuItem.addEventListener('click', () => {
                    displayTextByTitle(title);
                });
                dropdownItems.appendChild(menuItem);
            });
        })
        .catch(error => {
            console.error('Error fetching JSON data:', error);
        });
}
