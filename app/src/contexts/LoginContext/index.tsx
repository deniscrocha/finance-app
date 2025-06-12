import Login from "@/service/login";
import { useEffect, useState, type PropsWithChildren } from "react";
import { LoginContext } from "./LoginContext";
import axios, { HttpStatusCode } from "axios";
import { ApiRoutes } from "@/constants/ApiRoutes";
import { config } from "zod/v4";

const SESSION_STORAGE_KEY = "token";

export default function LoginContextProvider({ children }: PropsWithChildren){
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  function login(email: string, password: string){
    (async ()=>{
      const value = await Login(email, password);
      if(value){
        setToken(value.token);
        sessionStorage.setItem(SESSION_STORAGE_KEY, value.token);
      }
    })();
  }

  async function getKeyFromLocalStorage(){
    setIsLoading(true);
    const key = sessionStorage.getItem(SESSION_STORAGE_KEY);
    if(key){
      try{
        const response = await axios.get(ApiRoutes.VALIDATE_TOKEN, { headers: {Authorization: "Bearer "+key}});
        if(response.status === HttpStatusCode.Accepted){
          setToken(key);
        }
      }catch{
        sessionStorage.removeItem(SESSION_STORAGE_KEY);
      }finally{
        setIsLoading(false);
      }
    }
  }

  useEffect(()=>{
    if(!token){
      getKeyFromLocalStorage();
    }
  }, [token])

  return(
    <LoginContext.Provider value={{token, login, isLoading}}>
      {children}
    </LoginContext.Provider>
  )
}
