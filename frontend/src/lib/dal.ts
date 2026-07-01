import 'server-only'
import {cache} from "react";
import {cookies} from "next/headers";
import {redirect} from "next/navigation";
import {NextRequest} from "next/server";

export const verifySession = cache(async () => {
    const cookie = await cookies();
    const accessToken = cookie.get('accessToken')?.value;

    if (!accessToken) {
        redirect('/backoffice/login');
    }

    return {accessToken};
})