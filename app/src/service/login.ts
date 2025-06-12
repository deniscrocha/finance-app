import { ApiRoutes } from "@/constants/ApiRoutes";
import axios, { AxiosError } from "axios";

export default async function Login(email: string, password: string) {
  try {
    const request = await axios.post(ApiRoutes.LOGIN, { email: email, password: password });
    if (request.status === 200) {
      const token: string = request.data.token;
      return { token };
    }
  } catch (e) {
    if (e instanceof AxiosError) {
      console.log(e.response);
      return null;
    }
    console.log(e);
  }
  return null;
}
