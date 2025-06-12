import { Button } from "@/components/ui/button";
import { Card, CardAction, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Routes } from "@/constants/Routes";
import { useLogin } from "@/contexts/LoginContext/UseLogin";
import { Label } from "@radix-ui/react-label";
import { z } from "zod/v4";
import { useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router";
import useRegister from "@/contexts/RegisterContext/UseRegister";

const RegisterSchema = z.object({
  name: z.string()
    .min(5, { error: "O nome deve ter pelo menos 5 caracteres" })
    .max(100, { error: "O nome não deve ter mais de 100 caracteres" }),
  email: z.email("O email não está em um formato valido")
    .min(1, { error: "Email não pode estar vazio" }),
  password: z.string().min(4, { error: "A senha deve ter no minimo 4 caracteres" })
});

export default function Register() {
  const navigate = useNavigate();
  const { token } = useLogin();
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const { register, error, status, clear } = useRegister();

  function action() {
    if (password !== confirmPassword) {
      // TODO: modal with diferent passwords error;
      return;
    }
    try {
      RegisterSchema.parse({ name, email, password });
    } catch (e) {
      if (e instanceof z.ZodError) {
        // TODO: display modal with error;
        console.log(e.issues[0].message);
      }
      return;
    }
    register(name, email, password);
  }

  useEffect(() => {
    if (status === "error") {
      // TODO: modal error;
      console.log(error);
    }
    return (() => {
      clear();
    });
  }, [error, status, clear]);

  const buttonClassName = status === "loading" ? "cursor-progress" : "";

  if (status === "accepted")
    return <Navigate to={Routes.LOGIN} replace />
  if (token)
    return <Navigate to={Routes.HOME} replace />

  return (
    <div className="flex items-center justify-center h-screen w-full">
      <Card className="w-full max-w-sm">
        <CardHeader>
          <CardTitle>Cadastrar</CardTitle>
          <CardAction>
            <Button variant="link" onClick={() => { navigate(Routes.LOGIN) }}>Já tem login?</Button>
          </CardAction>
        </CardHeader>
        <CardContent>
          <form>
            <div className="flex flex-col gap-6">
              <div className="grid gap-2">
                <Label htmlFor="name">Nome</Label>
                <Input
                  id="name"
                  type="name"
                  placeholder="Sr Nome"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                />
              </div>
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
                </div>
                <Input
                  id="password"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <div className="grid gap-2">
                <div className="flex items-center">
                  <Label htmlFor="confirm-password">Confirme a senha</Label>
                </div>
                <Input
                  id="confirm-password"
                  type="password"
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  required
                />
              </div>
            </div>
          </form>
        </CardContent>
        <CardFooter className="flex-col gap-2">
          <Button variant="default" type="button" className={"w-full " + buttonClassName} onClick={action}>Entrar</Button>
        </CardFooter>
      </Card>
    </div>
  );
}
