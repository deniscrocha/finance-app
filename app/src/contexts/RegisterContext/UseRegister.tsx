import { use } from "react";
import { RegisterContext } from "./RegisterContext";

export default function useRegister(){
  return use(RegisterContext);
}
