import { use } from "react";
import { LoginContext } from "./LoginContext";

export function useLogin(){
  return use(LoginContext);
}
