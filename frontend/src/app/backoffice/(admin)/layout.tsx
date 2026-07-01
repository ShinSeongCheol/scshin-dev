import React from "react";
import {verifySession} from "@/src/lib";

export default async function adminLayout({children,}: {children: React.ReactNode}) {
    await verifySession();
    return(
        <>
            {children}
        </>
    )
}