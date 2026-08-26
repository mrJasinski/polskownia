import { Link } from "react-router-dom" ;

type HeaderProps = { title? : string; };

function Header({ title = "Polskownia" }: HeaderProps) {
  return (
    <header>
      <h2>{title}</h2>

      <nav>
        <Link to = "/" >Strona główna</Link>
        <Link to = "/articles" >Artykuły</Link>
        <Link to = "/contact" >Kontakt</Link>
      </nav>
    </header>
  );
}

export default Header;