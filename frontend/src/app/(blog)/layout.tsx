import React from "react";
import Header from "@/src/components/layout/header";
import Footer from "@/src/components/layout/footer";

export default function BlogLayout({children,}: {children: React.ReactNode}) {
    return (
        <>
            <Header />
            {children}
            <Footer />
        </>
    )
}