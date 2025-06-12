import { useLogin } from "@/contexts/LoginContext/UseLogin";

export default function Home(){
  const { token } = useLogin();
  return(
    <div className="w-full h-screen flex items-center justify-center">
      <h1>Home</h1>
      <p>AccessToken: {token}</p>
    </div>
  );
}
