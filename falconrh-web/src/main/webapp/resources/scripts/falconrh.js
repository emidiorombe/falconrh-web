PrimeFaces.locales['pt_BR'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],
    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
    dayNamesMin: ['D','S','T','Q','Q','S','S'],
    weekHeader: 'Semana',
    firstDay: 0,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText : 'Todo o Dia'
};

// Função para retirar os espaços em branco do início e do fim da string.
function trim(strTexto){
	return strTexto.replace(/^\s+|\s+$/g, '');
}

// --------------------------------funções de validações-----------------------------------------------------
function isDataValida(campo, pattern){
	var date=campo.valor;
	var ardt=new Array;
	var ExpReg = null;
	if(pattern=="DD/MM/YYYY"){
		ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");		
	}else if(pattern=="MM/YYYY"){
		ExpReg=new RegExp("(0[1-9]|1[012])/[12][0-9]{3}");
	}

	ardt=date.split("/");

	if (date.search(ExpReg)==-1){
		return false;
	} else if(((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30)){
		return false;
	} else if(ardt[1]==2){
		if ((ardt[0]>28)&&((ardt[2]%4)!=0)) return false;
		if ((ardt[0]>29)&&((ardt[2]%4)==0)) return false;
	}
	return true;
}

function isCEPValido(strCEP, blnVazio){
	// Caso o CEP não esteja nesse formato ele é inválido!
	var objER = /^[0-9]{2}\.[0-9]{3}-[0-9]{3}$/;
    strCEP = trim(strCEP);
    if(strCEP.length > 0){
    	if(objER.test(strCEP)) return true;
    	else return false;
	}else return blnVazio;
}

function isPISValido(pis){
	total=0;
	resto=0;
	numPIS=0;
	strResto="";

	numPIS=pis;
			
	if(numPIS=="" || numPIS==null){
		return false;
	}
	var i;
	for(i=0;i<=9;i++){
		resultado = (numPIS.slice(i,i+1))*(ftap.slice(i,i+1));
		total=total+resultado;
	}
	resto = (total % 11);
	if (resto != 0){
		resto=11-resto;
	}
	if(resto==10 || resto==11){
		strResto=resto+"";
		resto = strResto.slice(1,2);
	}
	if(resto!=(numPIS.slice(10,11))){
		return false;
	}
	return true;
}

function isCPFValido(obj) {
	var cpf = obj.value;
	if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999"){
		return false;
	}
	add = 0;
	var i;
	for (i=0; i < 9; i ++){
		add += parseInt(cpf.charAt(i)) * (10 - i);
	}
	rev = 11 - (add % 11);
	if (rev == 10 || rev == 11) rev = 0;
	if (rev != parseInt(cpf.charAt(9))) return false;
	add = 0;
	for (i = 0; i < 10; i ++){
		add += parseInt(cpf.charAt(i)) * (11 - i);
	}
	rev = 11 - (add % 11);
	if (rev == 10 || rev == 11) rev = 0;
	if (rev != parseInt(cpf.charAt(10))) return false;
	return true;
}


//--------------------- MASCARAS------------
function mascaraMoeda(obj, e){
    var SEPARADOR_MILESIMO = ".";
    var SEPARADOR_DECIMAL = ",";
	var key = '';
    var i = j = 0;
    var len = len2 = 0;
    var strCheck = '0123456789';
    var aux = aux2 = '';
    var whichCode = (window.Event) ? e.which : e.keyCode;
    if (whichCode == 13) return true;
    key = String.fromCharCode(whichCode); // Valor para o código da Chave
    if (strCheck.indexOf(key) == -1) return false; // Chave inválida
    len = obj.value.length;
    for(i = 0; i < len; i++)
        if ((obj.value.charAt(i) != '0') && (obj.value.charAt(i) != SEPARADOR_DECIMAL)) break;
    aux = '';
    for(; i < len; i++)
        if (strCheck.indexOf(obj.value.charAt(i))!=-1) aux += obj.value.charAt(i);
    aux += key;
    len = aux.length;
    if (len == 0) obj.value = '';
    if (len == 1) obj.value = '0'+ SEPARADOR_DECIMAL + '0' + aux;
    if (len == 2) obj.value = '0'+ SEPARADOR_DECIMAL + aux;
    if (len > 2) {
        aux2 = '';
        for (j = 0, i = len - 3; i >= 0; i--) {
            if (j == 3) {
                aux2 += SEPARADOR_MILESIMO;
                j = 0;
            }
            aux2 += aux.charAt(i);
            j++;
        }
        obj.value = '';
        len2 = aux2.length;
        for (i = len2 - 1; i >= 0; i--)
        obj.value += aux2.charAt(i);
        obj.value += SEPARADOR_DECIMAL + aux.substr(len - 2, len);
    }
    return false;
}




//adiciona mascara de cnpj
function mascaraCNPJ(cnpj){
	if(mascaraInteiro(cnpj)==false){
		event.returnValue = false;
	}       
	return formataCampo(cnpj, '00.000.000/0000-00', event);
}

//adiciona mascara de hora
function mascaraHora(hora){
	if(mascaraInteiro(hora)==false){
		event.returnValue = false;
	}
	return formataCampo(hora, '00:00', event);
}

//adiciona mascara de cep
function mascaraCep(cep){
	if(mascaraInteiro(cep)==false){
		event.returnValue = false;
	}       
	return formataCampo(cep, '00.000-000', event);
}

//adiciona mascara de data
function mascaraData(data){
	if(mascaraInteiro(data)==false){
		event.returnValue = false;
	}       
	return formataCampo(data, '00/00/0000', event);
}

//adiciona mascara ao telefone
function mascaraTelefone(tel){  
	if(mascaraInteiro(tel)==false){
		event.returnValue = false;
	}       
	return formataCampo(tel, '(00) 0000-0000', event);
}

//adiciona mascara ao CPF
function mascaraCPF(cpf){
	if(mascaraInteiro(cpf)==false){
		event.returnValue = false;
	}       
	return formataCampo(cpf, '000.000.000-00', event);
}

//valida telefone
function validaTelefone(tel){
	exp = /\(\d{2}\)\ \d{4}\-\d{4}/;
	if(!exp.test(tel.value)){
		alert('Numero de Telefone Invalido!');
	}
}

//valida CEP
function validaCep(cep){
	exp = /\d{2}\.\d{3}\-\d{3}/;
	if(!exp.test(cep.value)){
		alert('Numero de Cep Invalido!');               
	}
}

//valida data
function validaData(data){
	exp = /\d{2}\/\d{2}\/\d{4}/;
	if(!exp.test(data.value)){
		alert('Data Invalida!');                        
	}
}

//valida hora
function validaHora(hora){
	exp = /\d\d:\d\d/;
	if(!exp.test(hora.value))
		alert('Hora Invalida!');
}

//valida o CPF digitado
function validarCPF(Objcpf){
	var cpf = Objcpf.value;
	exp = /\.|\-/g;
	cpf = cpf.toString().replace( exp, "" ); 
	var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
	var soma1=0, soma2=0;
	var vlr =11;
	for(var i=0;i<9;i++){
		soma1+=eval(cpf.charAt(i)*(vlr-1));
		soma2+=eval(cpf.charAt(i)*vlr);
		vlr--;
	}       
	soma1 = (((soma1*10)%11)==10 ? 0:((soma1*10)%11));
	soma2=(((soma2+(2*soma1))*10)%11);

	var digitoGerado=(soma1*10)+soma2;
	if(digitoGerado!=digitoDigitado){
		alert('CPF Invalido!');         
	}
}

//valida numero inteiro com mascara
function mascaraInteiro(){
	if (event.keyCode < 48 || event.keyCode > 57){
		event.returnValue = false;
	return false;
	}
	return true;
}

//valida o CNPJ digitado
function validarCNPJ(objCnpj){
	var cnpj = objCnpj.value;
	var valida = new Array(6,5,4,3,2,9,8,7,6,5,4,3,2);
	var dig1= new Number;
	var dig2= new Number;

	exp = /\.|\-|\//g;
	cnpj = cnpj.toString().replace( exp, "" ); 
	var digito = new Number(eval(cnpj.charAt(12)+cnpj.charAt(13)));
			
	for(var i = 0; i<valida.length; i++){
		dig1 += (i>0? (cnpj.charAt(i-1)*valida[i]):0);  
		dig2 += cnpj.charAt(i)*valida[i];       
	}
	dig1 = (((dig1%11)<2)? 0:(11-(dig1%11)));
	dig2 = (((dig2%11)<2)? 0:(11-(dig2%11)));

	if(((dig1*10)+dig2) != digito){
		alert('CNPJ Invalido!');
	}
}

//formata de forma generica os campos
function formataCampo(campo, mascara, evento) { 
	var boleanomascara; 

	var Digitato = evento.keyCode;
	exp = /\:|\-|\.|\/|\(|\)| /g;
	campoSoNumeros = campo.value.toString().replace( exp, "" ); 

	var posicaoCampo = 0;    
	var novoValorCampo="";
	var tamanhoMascara = campoSoNumeros.length;; 
		
	if (Digitato != 8) { // backspace 
		for(var i=0; i<= tamanhoMascara; i++) { 
			boleanomascara  = ((mascara.charAt(i) == "-") || (mascara.charAt(i) == ".") || (mascara.charAt(i) == "/")  || (mascara.charAt(i) == ":")) ;
			boleanomascara  = boleanomascara || ((mascara.charAt(i) == "(") || (mascara.charAt(i) == ")") || (mascara.charAt(i) == " "));
			if (boleanomascara) { 
				novoValorCampo += mascara.charAt(i); 
				tamanhoMascara++;
			}else { 
				novoValorCampo += campoSoNumeros.charAt(posicaoCampo); 
				posicaoCampo++; 
			}              
		}      
		campo.value = novoValorCampo;
		return true; 
	}else { 
		return true; 
	}
}