'use server';

import {cookies} from "next/headers";
import {redirect} from "next/navigation";

export async function logout() {
    const cookie = await cookies();
    cookie.delete('accessToken');
    redirect('/backoffice/login')
}