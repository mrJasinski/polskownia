import { createContext, useContext, useState, useEffect, type ReactNode } from "react";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderProps 
{
  children: ReactNode;
}

interface AuthContextType 
{
  token: string | null;
  isLogged: boolean;
  login: (newToken: string) => void;
  logout: () => void;
}

export function isTokenValid(token : string | null) 
{
  if (!token) return false;

  try {
    const decoded = jwtDecode(token);

    if (decoded.exp == null)
        return false;

    return decoded.exp * 1000 > Date.now();
  } 
  catch 
  {
    return false;
  }
}

export function AuthProvider({ children } : AuthProviderProps) 
{
  const [token, setToken] = useState<string | null>(() => 
    { 
        const storedToken = localStorage.getItem("token");

        return isTokenValid(storedToken) ? storedToken : null;
    });

  const isLogged = Boolean(token);

  function login(newToken : string) 
  {
    if (!isTokenValid(newToken)) {
      throw new Error("Nieprawidłowy lub wygasły token");
    }

    localStorage.setItem("token", newToken);
    setToken(newToken);
  }

  function logout() 
  {
    localStorage.removeItem("token");
    setToken(null);
  }

  useEffect(() => 
    {
        if (!token) return;

        const decoded = jwtDecode<{ exp? : number }>(token);

        if (decoded.exp == null)
        {
            logout();
            return;
        }

        const remainingTime = decoded.exp * 1000 - Date.now();

        const timeout = setTimeout(logout, remainingTime);

        return () => clearTimeout(timeout);
    }, [token]);

  return ( 
        <AuthContext.Provider value = {{ token, isLogged, login, logout }} >
            {children}
        </AuthContext.Provider>
  );
}

export function useAuth(): AuthContextType 
{
  const context = useContext(AuthContext);

  if (context === null) 
    throw new Error( "useAuth must be used within an AuthProvider" );
  
  return context;
}