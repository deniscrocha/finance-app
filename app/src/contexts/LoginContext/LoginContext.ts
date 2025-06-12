import { createContext } from "react";

export interface ILoginContext {
  token: string | null;
  login: (email: string, password: string) => void;
  isLoading: boolean;
}

export const LoginContext = createContext({} as ILoginContext);
