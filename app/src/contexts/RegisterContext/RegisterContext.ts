import { createContext } from "react";

export type IRegisterStatus = "loading" | "accepted" | "error";

interface IRegisterContext {
  register: (name: string, email: string, password: string) => void;
  status:  IRegisterStatus | null; 
  error: string | null;
  clear: () => void;
};

export const RegisterContext = createContext({} as IRegisterContext);
