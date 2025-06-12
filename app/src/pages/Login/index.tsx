import { Button } from "@/components/ui/button"
import {
  Card,
  CardAction,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Routes } from "@/constants/Routes";
import { useLogin } from "@/contexts/LoginContext/UseLogin";
import { z } from "zod/v4";
import { useState } from "react";
import { Navigate, useNavigate } from "react-router";

const LoginSchema = z.object({
  email: z.email("O email não está em um formato valido")
    .min(1, { error: "Email não pode estar vazio" }),
  password: z.string().min(4, { error: "A senha deve ter no minimo 4 caracteres" })
});

export default function Login() {
  const { login, token } = useLogin();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  if (token !== null) {
    return <Navigate to={Routes.HOME} replace />
  }

  function submit() {
    try{
      LoginSchema.parse({ email, password });
    }catch (e){
      if(e instanceof z.ZodError){
        // TODO: popup modal with error
        console.log(e.issues[0].message);
      }
      return;
    }
    login(email, password);
  }

  return (
    <div className="flex items-center justify-center h-screen w-full">
      <Card className="w-full max-w-sm">
        <CardHeader>
          <CardTitle>Entrar</CardTitle>
          <CardAction>
            <Button variant="link" onClick={() => { navigate(Routes.REGISTER) }}>Cadastrar</Button>
          </CardAction>
        </CardHeader>
        <CardContent>
          <form>
            <div className="flex flex-col gap-6">
              <div className="grid gap-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  placeholder="exemplo@email.com"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="password">Senha</Label>
                  <a
                    href="#"
                    className="ml-auto inline-block text-sm underline-offset-4 hover:underline"
                  >
                    Esqueceu a senha?
                  </a>
                </div>
                <Input
                  id="password"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
            </div>
          </form>
        </CardContent>
        <CardFooter className="flex-col gap-2">
          <Button variant="default" type="button" className="w-full" onClick={submit}>Entrar</Button>
        </CardFooter>
      </Card>
    </div>
  )
}
