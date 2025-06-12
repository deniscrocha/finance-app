import { useState, type PropsWithChildren } from "react";
import { RegisterContext, type IRegisterStatus } from "./RegisterContext";
import axios, { AxiosError, HttpStatusCode } from "axios";
import { ApiRoutes } from "@/constants/ApiRoutes";

export default function RegisterContextProvider({ children }: PropsWithChildren){

  const [status, setStatus] = useState<IRegisterStatus | null>(null);
  const [error, setError] = useState<string | null>(null);
  

  async function register(name: string, email: string, password: string){
    setStatus("loading");
    setError(null);
    try{
      const response = await axios.post(ApiRoutes.REGISTER, {name, email, password});
      if(response.status === HttpStatusCode.Created){
        setStatus("accepted");
        return;
      }
    }catch(e){
      setStatus("error");
      if(e instanceof AxiosError){
        if(e.response?.data.message === "Email already in use"){
          setError("Email já está cadastrado");
          return;
        }
        setError(e.response?.data.message);
      }
    }
  }

  function clear(){
    setStatus(null);
    setError(null);
  }

  return (
    <RegisterContext.Provider value={{register, status, error, clear}}>
      {children}
    </RegisterContext.Provider>
  )
}
