import './App.css';


export async function showResult()
{
  var input = document.getElementById("elementsInput");
  var value = input.value;
  const objJson = encodeURI(JSON.stringify({elements: value}));
  console.log(objJson);
  const response = await fetch(
    `/fibonacci/${objJson}`
    );

  var data = await response.json();
  console.log(data);

  document.getElementById("fibonacciresult").innerHTML = data;
}

function clear()
{
  document.getElementById("fibonacciresult").innerHTML = "";
}

function ReturnResultButton() 
{

  return(
    <button onClick={showResult}>Calculate Results</button>
  );
}

function ClearResultButton()
{
  return(
    <button onClick={clear}>Clear Results</button>
  )
}


function App() {
  return (
    <div>
    <h1>Fibonacci Calculator</h1> 
    <label>No. of Elements: </label><input id="elementsInput" placeholder={"Number of elements"}></input> &nbsp;
    <ReturnResultButton /> &nbsp; <ClearResultButton/>
    <p id="fibonacciresult"> </p>
    
  </div>
  );
}

export default App;
