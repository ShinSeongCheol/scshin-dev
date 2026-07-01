import React from "react";

export default async function BackofficeLayout({children,}: {children: React.ReactNode}) {
    return(
        <main className={'flex-1 flex items-center justify-center bg-gray-50'}>{children}</main>
    )
}