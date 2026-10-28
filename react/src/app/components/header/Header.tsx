import { useAuth } from "../../services/AuthContext";
import "./Header.css";
import { Link } from "react-router-dom" ;

type HeaderProps = { title? : string; };

function Header({ title = "Polskownia" }: HeaderProps) 
{
  const { isLogged } = useAuth();

  return (
    <header>
      <h2>{title}</h2>

      <nav>
        <Link to = "/" >Strona główna</Link>
        <Link to = "/shop" >Sklep</Link>
        <Link to = "/articles" >Artykuły</Link>
        <Link to = "/contact" >Kontakt</Link>
        <Link to = "/calendar" >Kalendarz</Link>
        <Link to = "/g-calendar" >Kalendarz google</Link>
        <Link to = "/appointments" >Umów spotkanie</Link>
        <Link to = {isLogged ? "/dashboard" : "/auth"} >Konto</Link> ||| 
        <button>Wyloguj</button>
      </nav>
    </header>
  );
}

export default Header;