function sendInfos(){
    var email = document.getElementById(email).value
    var phone = document.getElementById(phone).value

    if(email !== '' && phone !== '') {
        document.getElementById('two').classList.remove('hidden')
        document.getElementById('two').classList.add('displayed')
        document.getElementById('one').classList.remove('displayed')
        document.getElementById('one').classList.add('hidden')

        document.getElementById(email).value = email
        document.getElementById(phone).value = phone


    }


}