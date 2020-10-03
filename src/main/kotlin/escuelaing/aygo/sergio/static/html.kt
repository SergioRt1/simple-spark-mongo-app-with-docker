package escuelaing.aygo.sergio.static

const val HOME_PAGE = """
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Words API</title>
</head>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
    function submitForm() {
        var input = document.getElementById('input');

        var headers = {'Content-Type': 'application/json'};
        var Url = "/save?word="+ input.value;
        axios({
            method: 'get',
            url: Url
        }).then(data => document.getElementById('response').innerHTML = "Request with response: " + data.data) 
        .catch(err => alert(err));
        requestWords();
        input.value = '';
    }
    function requestWords() {
        var headers = {'Content-Type': 'application/json'};
        var Url = "/words";
        axios({
            method: 'get',
            url: Url
        }).then(data => document.getElementById('words').innerHTML = "Current list of words:\n" + data.data)
        .catch(err => alert(err));
    }
    function search(ele) {
        if(event.key === 'Enter') {
            submitForm();        
        }
    }
</script>

<body bgcolor="#afafaf">
<center>
    <h4>API From EC2 using Docker</h4>
    <input id="input" type="text" placeholder="Word" onsubmit="submitForm()" onkeydown="search(this)"/><br>
    <input type="button" value="Submit" onclick="submitForm()"/><br><br>
    
    <label id="words"></label><br><br>
    
    <label id="response"></label><br>
</center>
</body>
</html>
"""