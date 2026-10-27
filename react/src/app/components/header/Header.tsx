import "./Header.css";
import { Link } from "react-router-dom" ;

type HeaderProps = { title? : string; };

function Header({ title = "Polskownia" }: HeaderProps) {
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
      </nav>
    </header>
  );
}

export default Header;