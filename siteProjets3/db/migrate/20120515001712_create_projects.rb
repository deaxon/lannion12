class CreateProjects < ActiveRecord::Migration
  def change
    create_table :projects do |t|
      t.string :projectName
      t.text :description
      t.text :shortDescription
      t.timestamps
    end
  end
end
