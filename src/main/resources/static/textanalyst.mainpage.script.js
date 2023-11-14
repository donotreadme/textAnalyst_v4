function displayTextByTitle(title) {
    title = encodeURIComponent(title)
    const content = `http://localhost:8080/text/by/title?title=${title}&highlightDialog=true`;
    fetch(content)
        .then(response => response.json())
        .then(data => {
            var textarea = tinymce.get('centralTextarea');
            textarea.setContent(data.text.replaceAll("\n", "<br>"));
            updateMetrics(data.metric);
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
    const proofed = document.getElementById('checkProofed').checked;
    const content = `http://localhost:8080/text/by/proofed?value=${proofed}`;
    let dropdownItems = document.getElementById('titlesDropdown');
    while (dropdownItems.firstChild) {
        dropdownItems.removeChild(dropdownItems.firstChild);
      }
    fetch(content) 
        .then(response => response.json()) 
        .then(data => {            
            // Loop through the JSON data and create dropdown items
            data.forEach(item => {
                const title = item;
                const menuItem = document.createElement('a');
                menuItem.className = 'dropdown-item';
                menuItem.textContent = title;
                menuItem.addEventListener('click', () => {
                    displayTextByTitle(title);
                });
                dropdownItems.appendChild(menuItem);
                dropdownItems.remo
            });
        })
        .catch(error => {
            console.error('Error fetching JSON data:', error);
        });
}

function highlightDirectSpeech(editor) {/*
    var content = editor.getContent();
    var directSpeechRegex = /"(.*?)"/g; // TODO decide if to handle this front or backend
    var highlightedContent = content.replace(directSpeechRegex, '<strong>"$1"</strong>');
	editor.setContent(highlightedContent);*/
}
