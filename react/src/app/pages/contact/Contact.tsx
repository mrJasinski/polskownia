function Contact() 
{
    function sendForm()
    {

    }

    return (

        <>
            <h2>Kontakt!</h2>

            <p>Telefon</p>
            <p>mail</p>
            <p>youtube</p>
            <p>facebook</p>
            <p>instagram</p>

            <form onSubmit={sendForm}>
                

                <p>formularz kontaktowy</p>
                <div>
                    <label>Adres e-mail: </label>
                    <input type = "email"></input>    
                </div>
                
                <div>
                    <label>Telefon: </label>
                    <input type = "tel"></input>    
                </div>
                
                <div>
                    <label>Treść: </label>
                    <input type = "text"></input>    
                </div>

                <div>
                    <button>Wyślij</button>
                </div>
            </form>

        </>
    );
}

export default Contact;