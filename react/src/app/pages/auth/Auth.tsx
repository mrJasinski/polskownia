import { useState, type FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../services/AuthContext";
import { APP_URL } from "../../constans/AppConstans";

export default function Auth()
{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const [ isLoginMode, setIsLoginMode ] = useState(true);

    const { login } = useAuth();
    const navigate = useNavigate();

    const isFormValid = email.trim() !== "" && password.trim() !== "" && (isLoginMode || password === confirmPassword);

    async function handleSubmit(e: FormEvent<HTMLFormElement>) 
    {
        e.preventDefault();
        setError("");
        setLoading(true);

        try 
        {
            const response = await fetch(`${APP_URL}/auth`, 
                {
                    method: "POST",
                    headers: 
                        {
                            "Content-Type": "application/json",
                        },
                    body: JSON.stringify({ email, password }),
                });

            const data: { token?: string; message?: string } = await response.json();

            if (!response.ok)
                throw new Error(data.message || "Nieprawidłowy email lub hasło");
            
            if (!data.token) 
                throw new Error("Serwer nie zwrócił tokena.");
            

            login(data.token);
            navigate("/dashboard", { replace: true });
        }
        catch (err: unknown) 
        {
            setError(
                err instanceof Error
                ? err.message
                : "Wystąpił błąd podczas logowania."
            );
        } 
        finally
        {
            setLoading(false);
        }
    }
        
    return(
        <form onSubmit = { handleSubmit } >
            <h2>Logowanie!</h2>
            <div>
                <label>Login: </label>
                <input type = "email" value = {email} onChange = {(e) => setEmail(e.target.value)} required ></input>
            </div>
            <div>
                <label>Hasło: </label>
                <input type = "password" value = {password} onChange = {(e) => setPassword(e.target.value)} required ></input>
            </div>
            {
                !isLoginMode && 
                (
                    <div >
                        <label>Powtórz hasło: </label>
                            <input type = "password" value = {confirmPassword} onChange = {(e) => setConfirmPassword(e.target.value)} required ></input>
                        </div>
                )
            }
            <button type = "submit" disabled = {!isFormValid} > { isLoginMode ? "Logowanie" : "Rejestracja"} </button>
            <button>Reset</button>
            <button type = "button" onClick = { () => setIsLoginMode((prev) => !prev) } >Przejdź do { isLoginMode ? "rejestracji" : "logowania" } </button>
            <button>Przypomnij hasło</button>
        </form>
    );

}