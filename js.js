function sendInfos(){
    var email = document.getElementById(email).valueOf()
    var phone = document.getElementById(phone).valueOf()

    if(email !== '' && phone !== '') {
        document.getElementById('two').classList.remove('hidden')
        document.getElementById('two').classList.add('displayed')
        document.getElementById('one').classList.remove('displayed')
        document.getElementById('one').classList.add('hidden')
    }


}