import LoginAction from "@/actions/LoginAction";

export default function LoginComponent(){
    return (
        <form action={LoginAction} className="flex flex-col gap-6">
            <input type="email" name="email" className="bg-zinc-100 rounded-s text-zinc-900 px-10 py-1 text-xl"/>
            <input type="password" name="password" className="bg-zinc-100 rounded-s text-zinc-900 px-10 py-1 text-xl" />
            <input type="submit" value={'Enviar'} className="bg-cyan-950 text-cyan-50 px-10 py-2 rounded-s cursor-pointer"/>
        </form>
    );
}