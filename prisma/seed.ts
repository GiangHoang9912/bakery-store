import { PrismaClient } from '@prisma/client'
import fs from 'fs'
import path from 'path'

const prisma = new PrismaClient()

async function main() {
  const publicDir = path.join(process.cwd(), 'public')
  const files = fs.readdirSync(publicDir)

  // Lọc ra các file ảnh
  const imageFiles = files.filter(file =>
    /\.(jpg|jpeg|png|gif)$/i.test(file)
  )

  // Tạo products từ các file ảnh
  const products = imageFiles.map(file => {
    // Chuyển tên file thành tên sản phẩm
    const name = file
      .replace(/\.(jpg|jpeg|png|gif)$/i, '')  // Xóa phần mở rộng
      .split('-')                             // Tách theo dấu gạch ngang
      .map(word => word.charAt(0).toUpperCase() + word.slice(1)) // Viết hoa chữ đầu
      .join(' ')                              // Nối lại thành chuỗi

    // Random giá từ 10,000 đến 100,000
    const price = Math.floor(Math.random() * (100000 - 10000) + 10000)

    return {
      name,
      price: parseFloat(price.toString()),
      image: `/public/${file}`
    }
  })

  // Tạo records trong database
  for (const product of products) {
    await prisma.products.create({
      data: product
    })
  }

  console.log('Seeding completed!')
}

main()
  .catch((e) => {
    console.error(e)
    process.exit(1)
  })
  .finally(async () => {
    await prisma.$disconnect()
  })
