import React from "react";

export default function MenuLayout({children}: {children: React.ReactNode}) {
    return(
        <div className={'flex-1 flex justify-center p-4'}>
            <div className={'w-full h-full p-2 bg-white rounded-2xl shadow-xl border border-gray-200'}>
                {children}
            </div>
        </div>
    )
}