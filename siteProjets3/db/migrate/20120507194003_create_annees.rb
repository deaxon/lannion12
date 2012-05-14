class CreateAnnees < ActiveRecord::Migration
  def change
    create_table :annees do |t|
      t.integer :year
      t.boolean :invisible_annee

      t.timestamps
    end
  end
end
