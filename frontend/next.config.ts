import type { NextConfig } from "next";

const imageBaseUrl = process.env.NEXT_PUBLIC_IMAGE_BASE_URL || 'http://localhost:8080';
const parsedImageUrl = new URL(imageBaseUrl);

const nextConfig: NextConfig = {
  /* config options here */
    images: {
        dangerouslyAllowLocalIP: process.env.NODE_ENV === "development",
        remotePatterns: [
            {
                protocol: parsedImageUrl.protocol.replace(':', '') as 'http' | 'https',
                hostname: parsedImageUrl.hostname,
                port: parsedImageUrl.port,
                pathname: '/uploads/**'
            }
        ]
    }
};

export default nextConfig;
