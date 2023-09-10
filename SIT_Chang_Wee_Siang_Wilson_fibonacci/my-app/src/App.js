import './App.css';


export async function showResult()
{

  var input = document.getElementById("elementsInput");
  var value = input.value;
  //if the input is invalid or number is lower/larger than the given range, instead of calling the api, show an error message
  if(value < 1 || value > 100 || isNaN(value))
  {
    document.getElementById("fibonacciresult").innerHTML = "Invalid input entered. Please enter an integer between 1 and 100";
  }

  else
  {
    const objJson = JSON.stringify({elements: value});
    console.log(objJson);
    const response = await fetch('/fibonacci', {method: 'POST', headers: { 'Content-Type': 'application/json', } , body: objJson});
    var data = await response.json();
    document.getElementById("fibonacciresult").innerHTML = "Result:  " +  JSON.stringify(data);
  }
  
}

function clear()
{
  document.getElementById("fibonacciresult").innerHTML = "";
}

function ReturnResultButton() 
{

  return(
    <button type="submit" onClick={showResult}>Calculate Results</button>
  );
}

function ClearResultButton()
{
  return(
    <button onClick={clear}>Clear</button>
  )
}


function App() {
  return (
    <div>
      <div class="row">
        <h1>Fibonacci Calculator</h1> 
        <h2>Please enter the number of elements, between 1 and 100</h2>
        <label>No. of Elements: </label>
        <input id="elementsInput" placeholder={"Number of elements"} ></input> &nbsp;
        <ReturnResultButton /> <ClearResultButton/>
      </div>

      <div class="row">
        <p id="fibonacciresult"></p>
      </div>
    
  </div>
  
  );
}

export default App;
