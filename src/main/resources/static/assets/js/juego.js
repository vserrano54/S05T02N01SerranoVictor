
let deck1 =[];
let deck2 =[];

let puntosJugador = 0;
    
    

const txtJugador = document.querySelector('#txtJugador');
const txtDado1 = document.querySelector('#txtDado1');
const txtDado2 = document.querySelector('#txtDado2');



const btnGuardarJugada = document.querySelector('#guardarJugada');
const btnTirar = document.querySelector('#btnTirar');
const btnNuevo = document.querySelector('#btnNuevo');
const divCartasJugador = document.querySelector('#jugador-dados');
const puntosHTML = document.querySelectorAll('small');

const botonGuardarTirada = document.getElementById('btbguardartirada');


 var nombreUsuario = document.getElementById("nombreUsuario").textContent;
 txtJugador.value =  nombreUsuario;
 
 

const crearDeck1 = () => {
        for (let i = 1; i <=6; i++ ){
            deck1.push(i+'A');          
        } 
    
    return deck1;
}

const crearDeck2 = () => {
    for (let i = 1; i <=6; i++ ){
        deck2.push(i+'B');          
    } 

return deck2;
}



crearDeck1();
crearDeck2();


deck1 = _.shuffle(deck1);
deck2 = _.shuffle(deck2);

console.log(deck1);
console.log(deck2);



const tirarDado1=() => {
    if (deck1.length===0){
        throw 'No tiene dados';
    }
    const dado =deck1.pop();
   
    return dado;
}

const tirarDado2=() => {
    if (deck2.length===0){
        throw 'No tiene dados';
    }
    const dado =deck2.pop();
   
    return dado;
}


const valorDado = (dado) => {
    const valor = dado.substring(0,dado.length-1); 
    return valor * 1;
}

const dadosImg = document.querySelector('.dados');


btnTirar.addEventListener('click', ()=> {
	 btnTirar.disabled=true;

    console.clear();
    crearDeck1();
    crearDeck2();


    deck1 = _.shuffle(deck1);
    deck2 = _.shuffle(deck2);
    puntosJugador = 0;
    resultado = 0;


    puntosHTML[0]=0;
    divCartasJugador.innerHTML='';
    puntosHTML[0].innerText = "0";




    const dado1 = tirarDado1();
    const dado2 = tirarDado2();
    
    
    
    console.log(valorDado(dado1));
    console.log(valorDado(dado2));
    
   
	puntosJugador = valorDado(dado1) + valorDado(dado2);
	 puntosHTML[0].innerText = puntosJugador; 
	
    console.log(puntosJugador);

   txtDado1.value=valorDado(dado1);
   txtDado2.value=valorDado(dado2);


   const imgDado1 = document.createElement('img');
   imgDado1.src =  `/assets/dados/${dado1}.jpg`;
   imgDado1.classList.add('dados');
   divCartasJugador.append(imgDado1);
   

   const imgDado2 = document.createElement('img');
   imgDado2.src =  `/assets/dados/${dado2}.jpg`;
   imgDado2.classList.add('dados');
   divCartasJugador.append(imgDado2);
   
  
   
   
    dadosImg.classList.add('dados');
  
  // Elimina la clase de animación después de la duración de la animación
  setTimeout(() => {
    dadosImg.classList.remove('dados');

   
  }, 2000); 


   setTimeout( () => {

    if (puntosJugador != 7){
		 
        alert('Lo siento mucho, perdiste'); 
        
         btnGuardarJugada.click();
         

    }
    else{
		 
    alert('Flicidades, Ganaste');  
   
      btnGuardarJugada.click();
         
    }
   
   },2000);
   

   
   //btnTirar.disabled=true;

});



btnNuevo.addEventListener('click', ()=> {
    console.clear();
    crearDeck1();
    crearDeck2();


    deck1 = _.shuffle(deck1);
    deck2 = _.shuffle(deck2);
    puntosJugador = 0;

    puntosHTML[0]=0;
    divCartasJugador.innerHTML='';
    puntosHTML[0].innerText = "0";

    btnTirar.disabled=false;




});
