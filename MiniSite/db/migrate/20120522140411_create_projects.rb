class CreateProjects < ActiveRecord::Migration
  def change
    create_table :projects do |t|
      t.string :name
      t.string :title
      t.text :description
      t.string :image
      t.string :urlFile
      t.string :urlSite

      t.timestamps
    end
  end

  def self.down
    drop_table :projects
  end
end
