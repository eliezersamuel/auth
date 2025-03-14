'use server';
import { z } from "zod";

const schema = z.object({
    email: z.string().email(),
    password: z.string()
});

export default async function LoginAction(postData: FormData) {
    const data = {
        email: postData.get('email'),
        password: postData.get('password')
    }

    const safeParsedData = schema.safeParse(data);

    if(safeParsedData.success){
        const result = await fetch('http://localhost:8080/sign-in', {
          method: 'POST',
          body: JSON.stringify(safeParsedData.data),
          headers: { 'Content-Type': 'application/json' },
        });
    
        console.log(result);
    }

  }