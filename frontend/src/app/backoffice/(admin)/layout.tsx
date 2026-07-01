import React from "react";
import {verifySession} from "@/src/lib";
import MenuNavigation from "@/src/app/backoffice/(admin)/_components/MenuNavigation";

export default async function adminLayout({children,}: {children: React.ReactNode}) {
    await verifySession();
    return(
        <div className={'flex-1 flex min-h-screen'}>
            <MenuNavigation/>
            {children}
        </div>
    )
}